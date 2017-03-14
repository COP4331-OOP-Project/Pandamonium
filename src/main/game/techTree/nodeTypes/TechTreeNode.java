package game.techTree.nodeTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TechTreeNode {

    private List<TechTreeNode> parents;
    private List<TechTreeNode> children;
    private String name;
    private String description;
    private TechNodeImageEnum imageEnum;

    TechTreeNode(String name, String description, TechNodeImageEnum imageEnum) {
        this.name = name;
        this.description = description;
        this.imageEnum = imageEnum;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    TechTreeNode(String name, String description, TechNodeImageEnum imageEnum, TechTreeNode... parents) {
        this.name = name;
        this.description = description;
        this.imageEnum = imageEnum;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
        Collections.addAll(this.parents, parents);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TechNodeImageEnum getImageEnum() {
        return imageEnum;
    }

    public void setImageEnum(TechNodeImageEnum imageEnum) {
        this.imageEnum = imageEnum;
    }

    public List<TechTreeNode> getChildren() {
        return children;
    }

    public void addChild(TechTreeNode node) {
        this.children.add(node);
    }

    public List<TechTreeNode> getParents() {
        return parents;
    }

    public void addParent(TechTreeNode node) {
        this.parents.add(node);
    }

    public boolean canCompleteResearch() {
        boolean parentsCompleted = true;
        for (TechTreeNode node : this.parents) {
            if (!node.isResearchCompleted()) {
                parentsCompleted = false;
                break;
            }
        }
        return !this.isResearchCompleted() && parentsCompleted;
    }

    public abstract boolean isResearchCompleted();

    public abstract void doResearch();

}
