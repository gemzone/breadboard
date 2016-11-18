
/**
 * Common Function
 * 
 * @author		gemzone (gemzone@naver.com)
 * @version	1.4
 * @date		2011-08-26
 * 				2011-11-10
 * 				2011-11-22
 * 				2016-11-08
 */
package io.nzo.booth.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 페이징 계산
 * @author gemzone (gemzone@naver.com)
 */
public class Paging
{
	public static final Logger logger = LoggerFactory.getLogger(Paging.class);
	
	
	public static HashMap<String, Object> pagination(long _totalCount, int page, int size) 
	{
		HashMap<String, Object> map = new HashMap<>();
		
		//ModelMap map = new ModelMap();
		
		long totalCount = _totalCount;	// 전체 행 카운트
		long listCount = size;			// 한페이지에 보여지는 행개수 (목록에 보여지는 글제목 수)
		long pageCount = 9;			// 고정값 (하단 가로로 보여지는 페이지수)
		long currentPage = page;		// 현재 보는 몇 페이지인지
		long totalPageCount = 0;		// calc
		long startPage = 0;			// calc
		long endPage = 0;				// calc
		long prevPage = 0;				// calc
		long nextPage = 0;				// calc
		
		totalPageCount = ( totalCount % listCount != 0) ? totalCount / listCount + 1 : totalCount / listCount;
		startPage = ( currentPage % pageCount != 0) ? ( currentPage / pageCount ) * pageCount + 1 : ( currentPage / pageCount - 1 ) * pageCount + 1;
		endPage = (startPage + pageCount - 1 > totalPageCount) ? totalPageCount : startPage + pageCount - 1;
		
		// 한페이지 앞으로 한페이지 전으로
		prevPage = (currentPage <= 1) ? 1 :  currentPage - 1;
		nextPage = (currentPage >= totalPageCount) ? totalPageCount : currentPage + 1;
		
		// 페이지 pageCount단위 앞으로 전으로
		long prevStepPage = (((long) (Math.ceil((double) currentPage / (double) pageCount)) - 1) * pageCount);
		long nextStepPage = ((((long) Math.ceil((double) currentPage / (double) pageCount)) * pageCount) + 1);
		
		// 최하치를 넘으려할경우
		if( prevStepPage <= 0 ) { prevStepPage = 1; }

		// 최대치를 넘길경우
		if( nextStepPage >= totalPageCount ) { nextStepPage = totalPageCount; }
		
		
//		map.addAttribute("totalCount", totalCount);
//		map.addAttribute("listCount", listCount);
//		map.addAttribute("pageCount", pageCount);
//		map.addAttribute("currentPage", currentPage);
//		map.addAttribute("totalPageCount", totalPageCount);
//		map.addAttribute("startPage", startPage);
//		map.addAttribute("endPage", endPage);
//		map.addAttribute("prevPage", prevPage);
//		map.addAttribute("nextPage", nextPage);
//		
//		map.addAttribute("page", currentPage);
//		map.addAttribute("size", listCount);
		
		map.put("totalCount", totalCount);
		map.put("listCount", listCount);
		map.put("pageCount", pageCount);
		map.put("currentPage", currentPage);
		map.put("totalPageCount", totalPageCount);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("prevStepPage", prevStepPage);
		map.put("nextStepPage", nextStepPage);

		ArrayList<Long> pages = new ArrayList<>();
		
		for( long i = startPage; i <= endPage; i++) 
		{
			pages.add(i);
		}
		map.put("pages", pages);
		
//		map.put("page", currentPage);
//		map.put("size", listCount);
        
		return map;
	}
	
	
}
