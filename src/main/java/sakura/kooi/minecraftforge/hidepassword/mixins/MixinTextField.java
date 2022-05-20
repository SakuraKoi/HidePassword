package sakura.kooi.minecraftforge.hidepassword.mixins;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sakura.kooi.minecraftforge.hidepassword.Hidepassword;

@Mixin(GuiTextField.class)
public class MixinTextField {
    @Shadow
    private String text;
    @Shadow
    private int enabledColor;

    @Redirect(method = "drawTextBox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;trimStringToWidth(Ljava/lang/String;I)Ljava/lang/String;"))
    public String adjustTrimStringToWidth(FontRenderer instance, String text, int width) {
        String out = instance.trimStringToWidth(text, width);
        out = Hidepassword.replaceChatText(out);
        return out;
    }

    @Redirect(method = "drawTextBox", at = @At (value = "FIELD", target = "Lnet/minecraft/client/gui/GuiTextField;enabledColor:I", opcode = Opcodes.GETFIELD))
    public int adjustColor(GuiTextField instance) {
        if (Hidepassword.getBeginWithSecretCommand(text) != null)
            return 0xff0000;
        return enabledColor;
    }
}
