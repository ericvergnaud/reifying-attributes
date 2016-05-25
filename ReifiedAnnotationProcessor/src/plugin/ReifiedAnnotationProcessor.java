package plugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import reified.Reified;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.Messager;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

public class ReifiedAnnotationProcessor implements AnnotationProcessor {

	AnnotationProcessorEnvironment	env;
	
	
	public ReifiedAnnotationProcessor(AnnotationProcessorEnvironment env) {
		this.env = env;
	}


	@Override
	public void process() {
		AnnotationTypeDeclaration annoDecl = (AnnotationTypeDeclaration)env.getTypeDeclaration(Reified.class.getName());
		Collection<Declaration> declarations = env.getDeclarationsAnnotatedWith(annoDecl);
		process(declarations);
	}
	
	private void process(Collection<Declaration> declarations) {
		Map<String, FieldDeclaration> reified = new HashMap<>();
		declarations.forEach((d)->
			checkReified(reified, d));
	}


	private void checkReified(Map<String, FieldDeclaration> reified, Declaration decl) {
		if(decl instanceof FieldDeclaration)
			checkReified(reified, (FieldDeclaration)decl);
		else if(decl instanceof TypeDeclaration)
			((TypeDeclaration)decl).getFields().forEach((f)->
				checkReified(reified, f));
	}
	
	private void checkReified(Map<String, FieldDeclaration> reified, FieldDeclaration decl) {
		FieldDeclaration existing = reified.get(decl.getSimpleName());
		if(existing==null)
			reified.put(decl.getSimpleName(), decl);
		else if(!existing.getType().equals(decl.getType())) {
			StringBuilder sb = new StringBuilder();
			sb.append("Type ");
			sb.append(decl.getType().toString());
			sb.append(" of ");
			sb.append(decl.getDeclaringType().getSimpleName());
			sb.append(".");
			sb.append(decl.getSimpleName());
			sb.append(" conflicts with type ");
			sb.append(existing.getType().toString());
			sb.append(" of ");
			sb.append(existing.getDeclaringType().getSimpleName());
			sb.append(".");
			sb.append(existing.getSimpleName());
			sb.append(".");
			Messager messager = env.getMessager();
			messager.printError(decl.getPosition(), sb.toString());
		}
	}

}
