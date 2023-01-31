export let resAddMenu = (res, store, query, setStore) => {
    let resData = res.data.data;
    alert(res.data.detail);

    let newCategories = store.storeCategories.filter((data, index) => {
        return index !== Number(query.get("f"));
    })

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

export let resDeleteMenu = (res, store, query, setStore) => {

    let categories = store.storeCategories.filter((data, index) => {
        return index !== Number(query.get("f"));
    })

    let category = store.storeCategories[Number(query.get("f"))];

    let updateMenu = category.storeMenus.filter((data, index) => {
        return index !== Number(query.get("c"));
    });

    category = {
        ...category,
        storeMenus: updateMenu
    }

    categories.splice(Number(query.get("f")), 0, category);

    setStore({
        ...store,
        storeCategories: categories
    });

}

export let resUpdateMenu = (res, store, query, setStore) => {

    let categories = store.storeCategories.filter((data, index) => {
        return index !== Number(query.get("f"));
    })

    let category = store.storeCategories[Number(query.get("f"))];

    let updateMenu = category.storeMenus.filter((data, index) => {
        return index !== Number(query.get("c"));
    });

    updateMenu.splice(Number(query.get("c")), 0, res.data.data);

    category = {
        ...category,
        storeMenus: updateMenu
    }

    categories.splice(Number(query.get("f")), 0, category);

    setStore({
        ...store,
        storeCategories: categories
    });

    return Number(query.get("c"));
}


// ------------------------------------ side --------------------------------------------

export let resDeleteSideMenu = (res, store, query, setStore) => {

    let sideCategories = store.storeSideCategories.filter((data, index) => {
        return index !== Number(query.get("f"));
    })

    let sideCategory = store.storeSideCategories[Number(query.get("f"))];

    let updateSideMenu = sideCategory.storeSideMenus.filter((data, index) => {
        return index !== Number(query.get("c"));
    });
    console.log(updateSideMenu)

    sideCategory = {
        ...sideCategory,
        storeSideMenus: updateSideMenu
    }

    sideCategories.splice(Number(query.get("f")), 0, sideCategory);

    console.log(sideCategories);

    setStore({
        ...store,
        storeCategories: sideCategories
    });

}