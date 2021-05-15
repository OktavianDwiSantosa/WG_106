package required;

public interface Consumable {


/*
    https://dzone.com/articles/to-interface-or-not-to-interface
    https://dzone.com/articles/programming-when-use
    http://www.javapractices.com/topic/TopicAction.do?Id=123
*/


    void used(Thing newOwner); // Material & MedKit memiliki penerapan used() yang berbeda

}
