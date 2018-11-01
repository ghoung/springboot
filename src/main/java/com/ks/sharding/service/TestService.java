package com.ks.sharding.service;

import com.ks.sharding.dto.test.TestDto;
import com.ks.sharding.entity.test.TestSearch;
import com.ks.sharding.pojo.TestTable;
import org.iemans.backend.dto.base.BaseResultWithPagerDto;
import org.iemans.backend.service.BaseService;

import java.util.List;

public interface TestService extends BaseService<TestTable> {

    BaseResultWithPagerDto<List<TestDto>> searchPage(TestSearch testSearch) throws Exception;
}
