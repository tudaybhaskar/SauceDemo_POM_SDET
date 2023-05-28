package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

<<<<<<< HEAD
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;
=======
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import listeners.RetryAnalyzer;
>>>>>>> f5175878247a5f893a0168f1ba8f5f385cd2c0cd

public class AnnotationTransformer implements IAnnotationTransformer {
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
<<<<<<< HEAD
	
=======
>>>>>>> f5175878247a5f893a0168f1ba8f5f385cd2c0cd

}
