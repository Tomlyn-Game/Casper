package moe.protasis.casper.api.sheet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moe.protasis.casper.util.IJsonSerializable;
import moe.protasis.casper.util.JsonWrapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SheetDescriptor implements IJsonSerializable {
    private float constant;

    @Override
    public void SerializeInternal(JsonWrapper json) {
        json.Set("constant", constant);
    }
}
