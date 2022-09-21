/**
 * 웹사이트에서 자주 사용되는 스크립트 모음
 */
 
 function getExt(path){
	index = path.lastIndexOf("."); //가장 마지막 점의 index
	
	return path.substring(index+1, path.length);
}