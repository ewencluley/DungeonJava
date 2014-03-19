package edu.sussex.tele.compiler;

public class AgentClassLoader extends ClassLoader
{
  public Class<?> loadThisClass(ClassInfo classInfo)
  {
	  byte[] classByte_ = classInfo.bytes;
	  Class<?> returnClass = defineClass(classInfo.className,
                             classByte_,
                             0,
                             classByte_.length);
	  resolveClass(returnClass);
	  return returnClass;
  }
}
