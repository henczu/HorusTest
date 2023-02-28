import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


interface Structure {
    // zwraca dowolny element o podanym kolorze
    Optional<Block> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

class Wall implements Structure {
    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color==null)  throw new IllegalArgumentException("Color cannot be null");
        if(blocks==null) return Optional.empty();
        return blocks.stream().filter(g -> color.equals(g.getColor())).findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material==null)  throw new IllegalArgumentException("Material cannot be null");
        if(blocks==null) return Collections.emptyList();
        return blocks.stream().filter(g -> material.equals(g.getMaterial())).collect(Collectors.toList());

    }

    @Override
    public int count() {
        int count=0;
        if(blocks==null) return count;
        for (Block block : blocks) {
            if(block.getClass().isInstance(CompositeBlock.class)){
                CompositeBlock compositeBlock = (CompositeBlock)block;
                count+=compositeBlock.getBlocks().size();
            }
            else {
                count++;
            }
        }
        return count;
    }
}
interface Block {
    String getColor();
    String getMaterial();
}

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
