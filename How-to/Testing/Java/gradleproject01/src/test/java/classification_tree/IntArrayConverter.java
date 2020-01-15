package classification_tree;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class IntArrayConverter implements ArgumentConverter {
 
    private void checkSource(Object source) {
        if (source == null) {
            throw new ArgumentConversionException("Cannot convert null source object");
        }
 
        if (!source.getClass().equals(String.class)) {
            throw new ArgumentConversionException(
                    "Cannot convert source object because it's not a string"
            );
        }
 
        String sourceString = (String) source;
        if (sourceString.trim().isEmpty()) {
            throw new ArgumentConversionException(
                    "Cannot convert an empty source string"
            );
        }
    }

	@Override
	public Object convert(Object source, ParameterContext arg1) throws ArgumentConversionException {
		// TODO Auto-generated method stub
		checkSource(source);
		 
        String sourceString[] = ((String) source).split(";");
        System.out.println(sourceString[0]);
        int [] array = new int[sourceString.length];
        for (int i=0; i<sourceString.length; i++)
        	array[i] = Integer.parseInt(sourceString[i]);
        
        return array;
	}

}
