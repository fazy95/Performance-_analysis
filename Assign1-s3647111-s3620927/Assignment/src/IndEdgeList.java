
public class IndEdgeList <T extends Object> {
	
	private T SourceVert;
	private T TargetVert;
	
	// Constructor for edge list
	public IndEdgeList(T Source, T Target) {
		this.SourceVert= Source;
		this.TargetVert= Target;
	}
	
	//Setters and Getters
	public T getsrcvertLabel() {
		return SourceVert;
	}
	public void setsrcvertLabel(T srcvertLabel) {
		this.SourceVert= srcvertLabel;
	}
	
	public T gettarvertLabel() {
		return TargetVert;
	}
	public void settarvertLabel(T tarvertLabel) {
		this.TargetVert= tarvertLabel;
	}
	
	public String OutputEdges() {
		
		return(new String(SourceVert+""+ TargetVert));
	}

}
