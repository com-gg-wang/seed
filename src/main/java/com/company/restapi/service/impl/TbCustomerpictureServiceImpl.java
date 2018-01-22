package com.company.restapi.service.impl;

import com.company.restapi.dao.TbCustomerpictureMapper;
import com.company.restapi.model.TbCustomerpicture;
import com.company.restapi.service.TbCustomerpictureService;
import com.company.restapi.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/18.
 */
@Service
@Transactional
public class TbCustomerpictureServiceImpl extends AbstractService<TbCustomerpicture> implements TbCustomerpictureService {
    @Resource
    private TbCustomerpictureMapper tbCustomerpictureMapper;

}