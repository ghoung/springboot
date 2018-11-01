package com.ks.sharding.service.impl;

import com.ks.sharding.dao.TestDao;
import com.ks.sharding.dto.test.TestDto;
import com.ks.sharding.entity.test.TestSearch;
import com.ks.sharding.pojo.TestTable;
import com.ks.sharding.service.TestService;
import org.iemans.backend.dto.base.BaseResultWithPagerDto;
import org.iemans.backend.dto.base.ResultWithPagerDto;
import org.iemans.backend.entity.base.SearchEntity;
import org.iemans.backend.service.Impl.BaseCustomerServiceImpl;
import org.iemans.backend.util.Constant;
import org.iemans.backend.util.SearchParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl extends BaseCustomerServiceImpl<TestTable, TestDao> implements TestService {

    /**
     * 单标简单查询 复杂请写xml
     *
     * @param testSearch
     * @return
     * @throws Exception
     */
    @Override
    public BaseResultWithPagerDto<List<TestDto>> searchPage(TestSearch testSearch) throws Exception {
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setPage(testSearch.getPage());
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put(SearchParam.SEARCH_EQ + "name", testSearch.getName());

        searchEntity.setSearchParams(searchParams);
        searchEntity.setSorts(testSearch.getSorts());
        ResultWithPagerDto<TestTable> superResult = super.pageSearch(searchEntity);

        List<TestDto> dtos = new ArrayList<TestDto>();
        for (TestTable testTable : superResult.getData()) {
            TestDto testDto = new TestDto();

            BeanUtils.copyProperties(testTable, testDto);
            dtos.add(testDto);
        }

        return new BaseResultWithPagerDto(Constant.SUCCESS_CODE, Constant.SEARCH_SUCCESS, dtos, superResult.getPage());
    }
}
