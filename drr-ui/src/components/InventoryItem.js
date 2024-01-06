import styles from "./InventoryDifference.module.css";
import {useSelector} from "react-redux";

export const InventoryItem = ({item}) => {
    const config = useSelector(state => state.config.value)
    let classNames = `${styles.imageBottom} ${styles.numberDisplay} ${item.changed > 0 ? styles.changedDisplayPositive : styles.changedDisplayNegative}`;

    let rarityClasses = new Map()
    rarityClasses['LEGENDARY'] = styles.legendary;
    rarityClasses['ASCENDED'] = styles.ascended;
    rarityClasses['EXOTIC'] = styles.exotic;
    rarityClasses['RARE'] = styles.rare;
    rarityClasses['MASTERWORK'] = styles.masterwork;
    rarityClasses['FINE'] = styles.fine;
    rarityClasses['BASIC'] = styles.basic;
    rarityClasses['JUNK'] = styles.junk;

    let itemDisplay = <div className={styles.imageContainer}>
        <img src={item.iconUrl} title={item.name} alt={item.name} className={`${styles.itemDisplay} ${rarityClasses[item.rarity]}`}/>
        <span
            className={`${styles.imageCentered} ${styles.numberDisplay}`}>{item.count > 1 ? `${item.count}x` : ''}</span>
        <span className={classNames}>{item.changed !== 0 ? `${item.changed}x` : ''}</span>
    </div>;

    function displayItem() {
        if(config.showChangedOnly) {
            return item.changed !== 0
        } else {
            return true
        }
    }
    return (
        <>
            {displayItem() ? itemDisplay : ''}
        </>
    )
}