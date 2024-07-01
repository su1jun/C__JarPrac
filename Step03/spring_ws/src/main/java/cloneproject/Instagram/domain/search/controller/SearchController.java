package cloneproject.Instagram.domain.search.controller;

import static cloneproject.Instagram.global.result.ResultCode.*;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import cloneproject.Instagram.domain.hashtag.dto.HashtagDto;
import cloneproject.Instagram.domain.member.dto.MemberDto;
import cloneproject.Instagram.domain.search.dto.SearchDto;
import cloneproject.Instagram.domain.search.service.SearchService;
import cloneproject.Instagram.global.result.ResultResponse;

@Slf4j
@Validated
@Api(tags = "검색 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/topsearch")
public class SearchController {

	private final SearchService searchService;

	@ApiOperation(value = "검색")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE001 - 검색에 성공하였습니다."),
		@ApiResponse(code = 400, message = "G003 - 유효하지 않은 입력입니다.\n"
			+ "G004 - 입력 타입이 유효하지 않습니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@ApiImplicitParam(name = "text", value = "검색내용", required = true, example = "dlwl")
	@GetMapping
	public ResponseEntity<ResultResponse> searchText(@RequestParam String text) {
		final List<SearchDto> searchDtos = searchService.searchByText(text);

		return ResponseEntity.ok(ResultResponse.of(SEARCH_SUCCESS, searchDtos));
	}

	@ApiOperation(value = "팔로잉 멤버 추천")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE009 - 팔로잉 추천 멤버 조회에 성공하였습니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@GetMapping("/recommend")
	public ResponseEntity<ResultResponse> getRecommendMembers() {
		final List<MemberDto> searchDtos = searchService.getRecommendMembers();

		return ResponseEntity.ok(ResultResponse.of(GET_RECOMMEND_MEMBER_SUCCESS, searchDtos));
	}

	@ApiOperation(value = "멤버 자동완성")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE007 - 멤버 자동완성 조회에 성공하였습니다."),
		@ApiResponse(code = 400, message = "G003 - 유효하지 않은 입력입니다.\n"
			+ "G004 - 입력 타입이 유효하지 않습니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@ApiImplicitParam(name = "text", value = "검색내용", required = true, example = "dlwl")
	@GetMapping("/auto/member")
	public ResponseEntity<ResultResponse> getMemberAutoComplete(@RequestParam String text) {
		final List<MemberDto> searchDtos = searchService.getMemberAutoComplete(text);

		return ResponseEntity.ok(ResultResponse.of(GET_MEMBER_AUTO_COMPLETE_SUCCESS, searchDtos));
	}

	@ApiOperation(value = "해시태그 자동완성")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE008 - 해시태그 자동완성 조회에 성공하였습니다."),
		@ApiResponse(code = 400, message = "G003 - 유효하지 않은 입력입니다.\n"
			+ "G004 - 입력 타입이 유효하지 않습니다.\n"
			+ "H004 - 해시태그는 #으로 시작해야 합니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@ApiImplicitParam(name = "text", value = "검색내용", required = true, example = "#dlwl")
	@GetMapping("/auto/hashtag")
	public ResponseEntity<ResultResponse> getHashtagAutoComplete(@RequestParam String text) {
		final List<HashtagDto> searchDtos = searchService.getHashtagAutoComplete(text);

		return ResponseEntity.ok(ResultResponse.of(GET_HASHTAG_AUTO_COMPLETE_SUCCESS, searchDtos));
	}

	@ApiOperation(value = "검색 조회수 증가, 최근 검색 기록 업데이트")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE002 - 검색 조회수 증가, 최근검색기록 업데이트에 성공하였습니다."),
		@ApiResponse(code = 400, message = "G003 - 유효하지 않은 입력입니다.\n"
			+ "G004 - 입력 타입이 유효하지 않습니다.\n"
			+ "G009 - 올바르지 않은 entity type 입니다.\n"
			+ "H004 - 해시태그는 #으로 시작해야 합니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "entityName", value = "조회수 증가시킬 식별 name", required = true, example = "dlwlrma"),
		@ApiImplicitParam(name = "entityType", value = "조회수 증가시킬 type", required = true, example = "MEMBER")
	})
	@PostMapping(value = "/mark")
	public ResponseEntity<ResultResponse> markSearchedEntity(@RequestParam String entityName,
		@RequestParam String entityType) {
		searchService.markSearchedEntity(entityName, entityType);

		return ResponseEntity.ok(ResultResponse.of(MARK_SEARCHED_ENTITY_SUCCESS));
	}

	@ApiOperation(value = "최근 검색 기록(15개 조회)")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE003 - 최근 검색 기록 15개 조회에 성공하였습니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@GetMapping(value = "/recent/top")
	public ResponseEntity<ResultResponse> getTop15RecentSearch() {
		final Page<SearchDto> searchDtos = searchService.getTop15RecentSearches();

		return ResponseEntity.ok(ResultResponse.of(GET_TOP_15_RECENT_SEARCH_SUCCESS, searchDtos));
	}

	@ApiOperation(value = "최근 검색 기록 무한스크롤")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE004 - 최근 검색 기록 페이지(무한스크롤) 조회에 성공하였습니다."),
		@ApiResponse(code = 400, message = "G003 - 유효하지 않은 입력입니다.\n"
			+ "G004 - 입력 타입이 유효하지 않습니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@GetMapping(value = "/recent")
	public ResponseEntity<ResultResponse> getRecentSearch(@Min(1) @RequestParam int page) {
		final Page<SearchDto> searchDtos = searchService.getRecentSearches(page);

		return ResponseEntity.ok(ResultResponse.of(GET_RECENT_SEARCH_SUCCESS, searchDtos));
	}

	@ApiOperation(value = "최근 검색 기록 삭제")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE005 - 최근 검색 기록 삭제에 성공하였습니다."),
		@ApiResponse(code = 400, message = "G003 - 유효하지 않은 입력입니다.\n"
			+ "G004 - 입력 타입이 유효하지 않습니다.\n"
			+ "G009 - 올바르지 않은 entity type 입니다.\n"
			+ "H004 - 해시태그는 #으로 시작해야 합니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "entityName", value = "삭제할 식별 name", required = true, example = "dlwlrma"),
		@ApiImplicitParam(name = "entityType", value = "삭제할 type", required = true, example = "MEMBER")
	})
	@DeleteMapping(value = "/recent")
	public ResponseEntity<ResultResponse> deleteRecentSearch(@RequestParam String entityName,
		@RequestParam String entityType) {
		searchService.deleteRecentSearch(entityName, entityType);

		return ResponseEntity.ok(ResultResponse.of(DELETE_RECENT_SEARCH_SUCCESS));
	}

	@ApiOperation(value = "최근 검색 기록 모두 삭제")
	@ApiResponses({
		@ApiResponse(code = 200, message = "SE006 - 최근 검색 기록 전체 삭제에 성공하였습니다."),
		@ApiResponse(code = 401, message = "M003 - 로그인이 필요한 화면입니다.")
	})
	@DeleteMapping(value = "/recent/all")
	public ResponseEntity<ResultResponse> deleteAllRecentSearch() {
		searchService.deleteAllRecentSearch();

		return ResponseEntity.ok(ResultResponse.of(DELETE_ALL_RECENT_SEARCH_SUCCESS));
	}

}
