import {useEffect, useState} from "react";

let CustomSelect = ({getData, onchange}) => {

    const [selectActive, setSelectActive] = useState(false);

    const [selectData, setSelectData] = useState({
        seq: null,
        name: "사이드 선택"
    });

    const [data, setData] = useState([]);

    useEffect(() => {

        if (getData !== undefined) {
            setData(getData);
            return;
        }

    }, [getData]);

    const onClickSelect = (seq, name) => {
        setSelectData({
            seq, name
        })
    }

    return (
        <div className={"m-60"}>
            <div className={"c-select-div"}>
                <div className={"c-select"} onClick={() => {
                    setSelectActive(!selectActive);
                }}>
                    <div className={"c-select-name"}>
                        <div>{selectData.name}</div>
                    </div>
                    <div className={"c-select-arrow"}>
                        {
                            selectActive === true ? (<div className={"under-arrow-animation"}>></div>) : (<div>></div>)
                        }
                    </div>
                </div>
                {
                    selectActive === true ? (
                        <div className={"c-select-list"}>
                            {
                                data.map((data, index) => (
                                    <div key={index} onClick={() => {
                                        onClickSelect(data.seq, data.name);
                                        setSelectActive(!selectActive);
                                    }}>{data.name}</div>
                                ))
                            }
                        </div>
                    ) : (<></>)
                }
            </div>
        </div>
    );
}
export default CustomSelect;