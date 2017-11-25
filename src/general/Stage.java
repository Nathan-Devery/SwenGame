package general;

/***
 * ENUM for stage/level of the game, associate the ENUM with a string.
 * There are 3 different levels - desert, forest and snow.
 *
 * @author Huon Fraser
 *
 */
public enum Stage {
	DESERT("desert"),
	FOREST("forest"),
	SNOW("snow");

	public final String fileName ;

	Stage(String fileName){
		this.fileName=fileName;
	}

	public String getFileName() {
		return fileName;
	}
}
