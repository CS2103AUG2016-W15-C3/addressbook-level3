package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Edit the name of a person in the address book.
 */
public class EditNameCommand extends Command{

    public static final String COMMAND_WORD = "edit-name";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the name of a person identified bu the index number used in the last person listing.\n\t"
            + "Parameters: INDEX n/NEW NAME\n\t"
            + "Example: " + COMMAND_WORD + " 1 n/Johnny Tan";
    
    public static final String MESSAGE_SUCCESS = "Contact updated: %1$s";
    
    private String newName;
    
    public EditNameCommand(int targetVisibleIndex, String newName) {
        super(targetVisibleIndex);
        this.newName = newName;
    }
    
    @Override
    public CommandResult execute() {
        try {
            Person target = (Person) getTargetPerson();
            Person newPerson = target;
            newPerson.setName(new Name(newName));
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
