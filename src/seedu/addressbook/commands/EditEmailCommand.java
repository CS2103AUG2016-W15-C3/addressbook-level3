package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Edit the email of a person in the address book.
 */
public class EditEmailCommand extends Command{

    public static final String COMMAND_WORD = "edit-email";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the email of a person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX n/NEW Email\n\t"
            + "Example: " + COMMAND_WORD + " 1 e/12345@gmail.com";
    
    public static final String MESSAGE_SUCCESS = "Contact updated: %1$s";
    
    private String newEmail;
    
    public EditEmailCommand(int targetVisibleIndex, String newEmail) {
        super(targetVisibleIndex);
        this.newEmail = newEmail;
    }
    
    @Override
    public CommandResult execute() {
        try {
            Person target = (Person) getTargetPerson();
            boolean checkPrivate = target.getEmail().isPrivate();
            Person newPerson = target;
            newPerson.setEmail(new Email(newEmail, checkPrivate));
            addressBook.removePerson(target);
            addressBook.addPerson(newPerson);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newPerson));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException ive) {
            return new CommandResult(ive.getMessage());
        }
    }
}
