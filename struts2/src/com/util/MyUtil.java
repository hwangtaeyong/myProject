package com.util;

public class MyUtil {
	
	//전체페이지수 구하기
	//numPerPage : 한화면에 표시할 데이터 갯수
	//dataCount : 전체 데이터 갯수
	public int getPageCount(int numPerPage, int dataCount){
		
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0)
			pageCount++;
		
		return pageCount;
		
	}
	
	//페이징 처리
	//currentPage : 현재 표시할 페이지
	//totalPage : 전체 페이지 수
	//listUrl : 링크를 설정할 URL
	public String pageIndexList(int currentPage,int totalPage, String listUrl){
		
		int numPerBlock = 5;
		int currentPageSetup;
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		if(currentPage==0 ||totalPage==0)
			return "";
		//list.jsp?pageNum=9
		//list.jsp?searchKey=name&searchValue=suzi&pageNum=9
		if(listUrl.indexOf("?")!=-1){
			listUrl = listUrl + "&";	
			//list.jsp?searchKey=name&searchValue=suzi&
		}else{
			listUrl = listUrl + "?";	
			//list.jsp?
		}
		
		//표시할 첫페이지에서 -1 한값
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		//◀이전
		if(totalPage>numPerBlock && currentPageSetup > 0){
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + 
					"\">◀이전</a>&nbsp;");			
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp;			
		}
		
		//바로가기 페이지
		page = currentPageSetup + 1;
		
		while(page<=totalPage && page <= (currentPageSetup + numPerBlock)){
			
			if(page == currentPage){
				
				sb.append("<font color=\"Fuchsia\"><b>" + page + "</b></font>&nbsp;");		
				//<font color="Fuchsia">9</font>&nbsp;
				
			}else{
				
				sb.append("<a href=\"" + listUrl + "pageNum=" + page +
						"\">" + page + "</a>&nbsp;");
				//<a href="list.jsp?pageNum=10">10</a>&nbsp;
			}
			
			page++;
		}
		
		//▶다음
		if(totalPage - currentPageSetup > numPerBlock){
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page +
					"\">▶다음</a>&nbsp;");			
			//<a href="list.jsp?pageNum=11">▶다음</a>&nbsp;
		}
		
		return sb.toString();		
		
	}

}

















