package parsing_json;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ElementCollection extends ArrayList<Element> {

    public Element findByAtomicNumber(int atomic_number) {

        Element match = new Element();

        for (Element element : this) {
            if (element.getNumber() == atomic_number) {
                match = element;
            }
        }
        return match;
    }

    public Element findByName(String name) {
        Element match = new Element();

        for (Element element : this) {
            if (element.getName().equalsIgnoreCase(name)) {
                match = element;
            }
        }
        return match;
    }

    public ElementCollection where(String fieldName, Object value) {

        ElementCollection elementsThatMatchValueOnField = new ElementCollection();

        if (classContainsAttribute(fieldName)) {

            for (int i = 0; i < this.size(); i++) {
                try {
                    Field privateStringName = this.get(i).getClass().getDeclaredField(fieldName);
                    privateStringName.setAccessible(true);
                    Object fieldValue = privateStringName.get(this.get(i));

                    if (fieldValue.equals(value)) {
                        elementsThatMatchValueOnField.add(this.get(i));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return elementsThatMatchValueOnField;
    }

    private boolean classContainsAttribute(String fieldName) {
        Class elementClass = Element.class;
        Field[] fieldNames = elementClass.getDeclaredFields();
        for (Field name : fieldNames) {
            if (name.getName().equalsIgnoreCase(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
