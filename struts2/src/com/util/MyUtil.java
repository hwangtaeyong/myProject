package com.util;

public class MyUtil {
	
	//��ü�������� ���ϱ�
	//numPerPage : ��ȭ�鿡 ǥ���� ������ ����
	//dataCount : ��ü ������ ����
	public int getPageCount(int numPerPage, int dataCount){
		
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0)
			pageCount++;
		
		return pageCount;
		
	}
	
	//����¡ ó��
	//currentPage : ���� ǥ���� ������
	//totalPage : ��ü ������ ��
	//listUrl : ��ũ�� ������ URL
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
		
		//ǥ���� ù���������� -1 �Ѱ�
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		//������
		if(totalPage>numPerBlock && currentPageSetup > 0){
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + 
					"\">������</a>&nbsp;");			
			//<a href="list.jsp?pageNum=5">������</a>&nbsp;			
		}
		
		//�ٷΰ��� ������
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
		
		//������
		if(totalPage - currentPageSetup > numPerBlock){
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page +
					"\">������</a>&nbsp;");			
			//<a href="list.jsp?pageNum=11">������</a>&nbsp;
		}
		
		return sb.toString();		
		
	}

}

















