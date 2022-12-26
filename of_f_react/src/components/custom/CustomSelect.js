import {useEffect, useState} from "react";

let CustomSelect = ({getData, defaultData, onchange}) => {

    const [selectActive, setSelectActive] = useState(false);

    const [selectData, setSelectData] = useState({
        seq: defaultData.seq,
        name: defaultData.name
    });

    const [data, setData] = useState([]);

    useEffect(() => {

        if (getData != null) {
            setData(getData);
        }

    }, []);

    return (
        <div style={{
            width: "100%"
        }}>
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
                                    <div key={index}>{data.name}</div>
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