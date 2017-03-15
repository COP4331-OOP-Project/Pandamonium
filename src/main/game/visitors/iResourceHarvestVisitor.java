package game.visitors;

import game.entities.managers.ResourceManager;
import game.resources.Resource;

public interface iResourceHarvestVisitor {
    Resource visitResourceManager(ResourceManager resourceManager);
}
