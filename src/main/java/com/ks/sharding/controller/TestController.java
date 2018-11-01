package com.ks.sharding.controller;

import com.ks.sharding.dao.TestDao;
import com.ks.sharding.dto.test.TestDto;
import com.ks.sharding.entity.test.*;
import com.ks.sharding.pojo.TestTable;
import com.ks.sharding.service.TestService;
import org.iemans.backend.controller.BaseController;
import org.iemans.backend.dto.base.BaseResultDto;
import org.iemans.backend.dto.base.BaseResultWithPagerDto;
import org.iemans.backend.entity.base.*;
import org.iemans.backend.entity.login.BaseLoginInfo;
import org.iemans.backend.util.Constant;
import org.iemans.backend.util.GetIpAndMac;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController<TestService> {
    private BaseLoginInfo customerInfoEntity = null;

    public TestController() {
        customerInfoEntity = new BaseLoginInfo();
        customerInfoEntity.setPhone("123123");
        customerInfoEntity.setId(-1L);
        customerInfoEntity.setName("admin");
        customerInfoEntity.setEmail("123@123.123");
    }

    @PostMapping("test")
    public TestTable test() throws Exception {


        TestTable testTable = service.selectById(249549629186637824L);

        return testTable;
    }

    /**
     * 添加单个记录
     *
     * @param testAddEntity
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    public BaseResultDto add(@RequestBody @Valid TestAddEntity testAddEntity, HttpServletRequest req) throws Exception {

        return super.add(testAddEntity, customerInfoEntity, req);
    }

    /**
     * 删除单个记录
     *
     * @param testDeleteEntity
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteById")
    public BaseResultDto deleteById(@RequestBody @Valid BaseDeleteEntity testDeleteEntity, HttpServletRequest req) throws Exception {

        testDeleteEntity.setDeleteUser(customerInfoEntity.getId());
        testDeleteEntity.setDeleteTime(new Date());
        testDeleteEntity.setDeleteIp(GetIpAndMac.getIp(req));
        this.service.deleteById(testDeleteEntity);

        return null;
//        return super.deleteById(testDeleteEntity, customerInfoEntity, req);
    }

    /**
     * 修改单个记录
     *
     * @param testUpdateEntity
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping("/updateById")
    public BaseResultDto updateById(@RequestBody @Valid TestUpdateEntity testUpdateEntity, HttpServletRequest req) throws Exception {
//       System.out.println(JSON.toJSONString(customerInfoEntity,SerializerFeature.WriteMapNullValue));
        return super.updateById(testUpdateEntity, customerInfoEntity, req);
    }

    /**
     * 获取单个记录
     *
     * @param singleLongEntity
     * @return
     * @throws Exception
     */
    @Override
    @PostMapping("/getById")
    public BaseResultDto getById(@RequestBody @Valid SingleLongEntity singleLongEntity) throws Exception {

        BaseResultDto baseResultDto = super.getById(singleLongEntity);
        TestTable testTable = (TestTable) baseResultDto.getData();
        TestDto testDto = new TestDto();
        BeanUtils.copyProperties(testTable, testDto);
        baseResultDto.setData(testDto);
        return baseResultDto;

    }

    @PostMapping("/getBatchIds")
    public BaseResultDto getBatchIds(@RequestBody @Valid MoreLongEntity ids) throws Exception {
        List<TestTable> lists = service.selectBatchIds(ids.getIds());
        BaseResultDto baseResultDto = new BaseResultDto(Constant.SUCCESS_CODE, Constant.SUCCESS, lists);
        return baseResultDto;

    }

    /**
     * 批量删除
     *
     * @param baseDeleteEntitys
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteBatchIds")
    public BaseResultDto deleteBatchIds(@RequestBody @Valid BaseDeleteEntitys baseDeleteEntitys, HttpServletRequest req) throws Exception {
        super.deleteBatchIds(baseDeleteEntitys, customerInfoEntity, req);

        return new BaseResultDto(Constant.SUCCESS_CODE, Constant.DELETE_SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param testSearch
     * @return
     * @throws Exception
     */
    @PostMapping("/pageSearch")
    public BaseResultWithPagerDto<List<TestDto>> pageSearch(@RequestBody @Valid TestSearch testSearch) throws Exception {

        BaseResultWithPagerDto<List<TestDto>> result = service.searchPage(testSearch);

        return result;
    }

}
