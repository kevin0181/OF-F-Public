export let resAddMenu = (res, store, query, setStore) => {
    let resData = res.data.data;
    alert(res.data.detail);

    let newCategories = store.storeCategories.filter((data, index) => {
        return index !== Number(query.get("f"));
    })

    console.log(resData);

    let updateCategory = store.storeCategories[Number(query.get("f"))];

    let updateMenu = [
        ...updateCategory.storeMenus,
        resData
    ];

    updateCategory = {
        ...updateCategory,
        storeMenus: updateMenu
    }

    newCategories.splice(Number(query.get("f")), 0, updateCategory);

    setStore({
        ...store,
        storeCategories: newCategories
    });

    return updateMenu.length - 1;

}