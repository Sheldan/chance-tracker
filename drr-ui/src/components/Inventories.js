import {useSelector } from 'react-redux'
import {CharacterInventory} from "./CharacterInventory";

export function Inventories() {
    const inventory = useSelector(state => state.inventory.value)
    return (
        <>
            <h2>Inventories</h2>
            {inventory.map(characterInventory =>
                <CharacterInventory
                    character={characterInventory}
                />
            )}
        </>
    );
}