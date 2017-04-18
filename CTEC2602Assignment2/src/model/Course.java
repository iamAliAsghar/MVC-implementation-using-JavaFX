package model;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Course implements java.io.Serializable {
	
	private String courseName;
	private Map<String, Module> modules;
	
	
	public Course(String courseName) {
		this.courseName = courseName;
		modules = new HashMap<String, Module>();
	}
	
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String name) {
		courseName = name;
	}
	
	public void addModule(Module m) {
		modules.put(m.getModuleCode(), m);
	}
	
	public Module getModuleByCode(String code) {
		return modules.get(code);
	}
	
	public Collection<Module> getModulesOnCourse() {
		return modules.values();
	}
	
	public int creditsPassed() {
		int credits = 0;
		for (Module m : getModulesOnCourse()) {
			if (!m.requireResit())
				credits+=30;
		}
		
		return credits;
	}
	
	public int yearAverageMark() {
		int mark = 0;
		for (Module m : getModulesOnCourse()) {
				mark += m.getModuleMark();
		}
		
		return (int) Math.round(mark/4.0);
	}
	
	public void resetModuleMarks() {
		for (Module m : getModulesOnCourse()) {
			m.setCwkMark(0);
			m.setExamMark(0);
		}
	}
	
	@Override
	public String toString() {
		//a non-standard toString that simply returns the course name,
		//so as to assist in displaying courses correctly in a ComboBox<Course>
		return courseName;
	}
	
	public String actualToString() {
		return "Course:[courseName=" + courseName + ", modules=" + modules + "]";
	}
	
}
