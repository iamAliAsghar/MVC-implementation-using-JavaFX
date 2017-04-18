package model;


public class Module implements java.io.Serializable{
	
	private String moduleCode;
	private String moduleName;
	private int examMark;
	private int cwkMark;
	private boolean cwkOnly;
	
	
	public Module(String moduleCode, String moduleName, boolean cwkOnly) {
		this(moduleCode, moduleName, 0, 0, cwkOnly);
	}
	
	public Module(String moduleCode, String moduleName, int examMark, int cwkMark, boolean cwkOnly) {
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.examMark = examMark;
		this.cwkMark = cwkMark;
		this.cwkOnly = cwkOnly;
	}
	
	
	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getExamMark() {
		return examMark;
	}
	
	public void setExamMark(int examMark) {
		this.examMark = examMark;
	}

	public int getCwkMark() {
		return cwkMark;
	}
	
	public void setCwkMark(int cwkMark) {
		this.cwkMark = cwkMark;
	}
	
	public boolean isCwkOnly() {
		return cwkOnly;
	}
	
	public void setCwkOnly(boolean cwkOnly) {
		this.cwkOnly = cwkOnly;
	}
	
	public int getModuleMark() {
		return cwkOnly ? cwkMark : (int) Math.round(((examMark + cwkMark) / 2.0));
	}
	
	public boolean requireResit() {
		return this.getModuleMark() < 40;
	}
	
	@Override
	public String toString() {
		return "Module:[moduleCode=" + moduleCode + ", moduleName="
				+ moduleName + ", examMark=" + examMark + ", cwkMark=" + cwkMark 
				+ "cwkOnly=" + cwkOnly + "]";
	}
	
}
