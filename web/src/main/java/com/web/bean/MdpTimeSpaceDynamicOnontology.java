package com.hikvision.mdp.web.bean;

import com.hikvision.mdp.web.pojo.MdpOntologyObjectModel;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectRelationship;

import java.util.List;

/**
 * Created by geting on 2017/2/8.
 */
public class MdpTimeSpaceDynamicOnontology {

    private List<String> legendData;
    private List<MdpCategoryObject> categoriesData;
    private List<MdpOntologyObjectModel> nodesData;
    private List<MdpOntologyObjectRelationship> linksData;

    public  MdpTimeSpaceDynamicOnontology(){}

    public MdpTimeSpaceDynamicOnontology(List<String> legendData, List<MdpCategoryObject> categoriesData, List<MdpOntologyObjectModel> nodesData, List<MdpOntologyObjectRelationship> linksData) {
        this.legendData = legendData;
        this.categoriesData = categoriesData;
        this.nodesData = nodesData;
        this.linksData = linksData;
    }

    public List<?> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<MdpCategoryObject> getCategoriesData() {
        return categoriesData;
    }

    public void setCategoriesData(List<MdpCategoryObject> categoriesData) {
        this.categoriesData = categoriesData;
    }

    public List<MdpOntologyObjectModel> getNodesData() {
        return nodesData;
    }

    public void setNodesData(List<MdpOntologyObjectModel> nodesData) {
        this.nodesData = nodesData;
    }

    public List<MdpOntologyObjectRelationship> getLinksData() {
        return linksData;
    }

    public void setLinksData(List<MdpOntologyObjectRelationship> linksData) {
        this.linksData = linksData;
    }

    @Override
    public String toString() {
        return "MdpTimeSpaceDynamicOnontology{" +
                "legendData=" + legendData +
                ", categoriesData=" + categoriesData +
                ", nodesData=" + nodesData +
                ", linksData=" + linksData +
                '}';
    }
}
