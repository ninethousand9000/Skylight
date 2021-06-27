package club.astro.client.modules.combat;

import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;

@ModuleAnnotation(category = ModuleCategory.Combat)
public class Test3 extends Module {
    public Test3 (ModuleCategory category) {
        this.category = category;
    }
}
