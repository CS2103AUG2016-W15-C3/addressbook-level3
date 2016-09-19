package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class FindByTagCommand extends Command {
    public static final String COMMAND_WORD = "find-tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose tags contain "
            + "any of the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " water fire";

    private final Set<String> keywords;

    public FindByTagCommand(Set<String> keywords) {
        this.keywords = keywords;
    }
    
    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeyword() {
        return keywords;
    }
    
    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithTagsContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    private List<ReadOnlyPerson> getPersonsWithTagsContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final UniqueTagList wordsInTags = person.getTags();
            for (String keyword:keywords) {
                try {
                    if (wordsInTags.contains(new Tag(keyword))) {
                        matchedPersons.add(person);
                    }
                } catch (IllegalValueException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return matchedPersons;
    }

}
