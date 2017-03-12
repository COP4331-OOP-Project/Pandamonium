package game.techTree.nodeTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TechTreeNode {

    private List<TechTreeNode> parents;
    private List<TechTreeNode> children;
    private String name;
    private String description;
    private boolean isResearchCompleted;

    TechTreeNode(String name, String description) {
        this.name = name;
        this.description = description;
        this.isResearchCompleted = false;
    }

    TechTreeNode(String name, String description, TechTreeNode... parents) {
        this.name = name;
        this.description = description;
        this.isResearchCompleted = false;
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

    public abstract boolean isResearchCompleted();

    public abstract void doResearch();
}
