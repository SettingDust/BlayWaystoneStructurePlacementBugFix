package settingdust.blaywaystonestructureplacementbugfix;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class BlayWaystoneAnnotationAdjuster implements MixinAnnotationAdjuster {

    @Override
    public AdjustableAnnotationNode adjust(
        final List<String> targetClassNames,
        final String mixinClassName,
        final MethodNode handlerNode,
        final AdjustableAnnotationNode annotationNode
    ) {
        return annotationNode;
    }
}
