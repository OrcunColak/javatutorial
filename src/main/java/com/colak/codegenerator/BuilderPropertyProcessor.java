package com.colak.codegenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("BuilderProperty")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class BuilderPropertyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if (element.getKind() != ElementKind.FIELD) {
                    processingEnv.getMessager().printMessage(
                            Diagnostic.Kind.ERROR,
                            "@BuilderProperty can only be applied to fields.",
                            element
                    );
                } else {
                    processingEnv.getMessager().printMessage(
                            Diagnostic.Kind.NOTE,
                            "Processing field: " + element.getSimpleName(),
                            element
                    );
                }
            }
        }
        return true; // No further processing of this annotation type
    }
}

