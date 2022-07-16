package com.jm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bstek.ureport.exception.ReportException;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.jm.mapper.UreportMapper;
import com.jm.model.UreportFilety;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Setter
@Component
@ConfigurationProperties(prefix = "ureport.mysql.provider")
public class MysqlPprovider implements ReportProvider {

    private static final String NAME = "mysql-provider";

    // 特定前缀，ureport底层会调用 getPrefix 方法来获取报表操作的Provier类
    private String prefix = "mysql:";

    // 是否禁用
    private boolean disabled = false;

    @Autowired
    private UreportMapper ureportFileMapper;
    /**
     * 加载数据指定
     * @param name
     * @return
     */
    @Override
    public InputStream loadReport(String name) {
        QueryWrapper<UreportFilety> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UreportFilety::getName,name);
        UreportFilety ureportFilety = ureportFileMapper.selectOne(queryWrapper);
        ByteArrayInputStream  inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(ureportFilety.getContent());
        }catch (Exception e){
            throw new ReportException(e);
        }

         return inputStream;
    }

    /**
     * 删除数据
     * @param name
     */
    @Override
    public void deleteReport(String name) {
        QueryWrapper<UreportFilety> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UreportFilety::getName,getCorrectName(name));
        ureportFileMapper.delete(wrapper);
    }

    /**
     * 获取所有的excel
     * @return
     */
    @Override
    public List<ReportFile> getReportFiles() {
        QueryWrapper<UreportFilety> queryWrapper = new QueryWrapper<>();
        List<UreportFilety> list =  ureportFileMapper.selectList(queryWrapper);
        List<ReportFile> reportProviders = new ArrayList<>();
        for (UreportFilety ureportFilety : list) {
            reportProviders.add(new ReportFile(ureportFilety.getName(),ureportFilety.getUpdateTime()));
        }
        return reportProviders;
    }

    /**
     * 保存excel
     * @param name
     * @param content
     */
    @Override
    public void saveReport(String name, String content) {
        name = getCorrectName(name);
        QueryWrapper<UreportFilety> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UreportFilety::getName,name);
        UreportFilety ureportFilety = ureportFileMapper.selectOne(queryWrapper);
        if(null == ureportFilety){
            UreportFilety filety = new UreportFilety();
            filety.setName(name);
            filety.setContent(content.getBytes());
            ureportFileMapper.insert(filety);
        }else {
            ureportFilety.setContent(content.getBytes());
            QueryWrapper<UreportFilety> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UreportFilety::getId,ureportFilety.getId());
            ureportFileMapper.update(ureportFilety,wrapper);
        }

    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean disabled() {
        return disabled;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * 获取没有前缀的文件名
     * @param name
     * @return
     */
    private String getCorrectName(String name){
        if(name.startsWith(prefix)){
            name = name.substring(prefix.length());
        }
        return name;
    }
}
