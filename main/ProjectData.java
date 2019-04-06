package inventory.main;

import java.io.Serializable;

public class ProjectData implements Serializable {
	String name = null, desc = null;
	private static final long serialVersionUID = 1L;
	boolean local;
	String[] tags = null;

}
