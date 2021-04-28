import React, {useState, useEffect, useRef} from "react"

import "./Search.css"
import {findMetrics, findModeledData, findServices} from "../../Api";
import {Button} from "../../Components";

const categories = [
    {name: "Metrics", request: findMetrics, key: "metrics"},
    {name: "Services", request: findServices, key: "services"},
    {name: "Modeled Data", request: findModeledData, key: "modeledDatas"},
];

const Search = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [filters, setFilters] = useState([]);
    const [search, setSearch] = useState("");
    const [options, setOptions] = useState([]);
    const menu = useRef(null);

    const getCategories = () => {
        if (filters.length !== 0) {
            return categories.filter(category => filters.includes(category.name));
        }
        return categories
    };

    useEffect(() => {
        if (isOpen) {
            handleSearch();
        }
    }, [isOpen, filters]);

    const handleSearch = () => {
        const categories = getCategories();
        Promise.all(categories.map(category => category.request(search)))
            .then(responses => Promise.all(responses.map(res => res.json())))
            .then(responses => {
                const newOptions = [];
                responses.forEach((res, i) => {
                    const {key, category} = categories[i];
                    res._embedded[key].forEach(asset => {
                        newOptions.push({name: asset.name, key: asset._links.self.href, category})
                    })
                });
                setOptions(newOptions);
            })
    };

    const onSubmit = async (e) => {
        e.preventDefault();

        setIsOpen(true);
        handleSearch()
    };


    const handleClickOutside = (e) => {
        if (menu.current && !menu.current.contains(e.target)) {
            setIsOpen(false);
        }
    };

    useEffect(() => {
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    });

    const addTag = (name) => {
        setFilters([...filters, name]);
    };

    const removeTag = (name) => {
        setFilters([...filters.filter(option => option !== name)]);
    };

    const renderTags = () => {
        return categories.map(category => {
            let className = " Tag";
            let onClick = () => addTag(category.name);
            if (filters.includes(category.name)) {
                className += " Tag-active";
                onClick = () => removeTag(category.name)
            }
            return <Button key={category.name} className={className} onClick={onClick}>{category.name}</Button>
        })
    };

    const renderItems = () => {
        return options.map(option => <div key={option.key} className="Dropdown-item" onClick={() => setIsOpen(false)}>
            <p className="Asset">{option.name}</p>
            <small className="Category">{option.category}</small>
        </div>)
    };

    const renderMenu = () => {
        if (isOpen) {
            return <div className="Dropdown-menu">
                <div className="Tags">
                    {renderTags()}
                </div>
                {renderItems()}
            </div>
        }
    };

    return <div className="Dropdown" ref={menu}>
        <div className="Dropdown-header">
            <form className="Form" onSubmit={onSubmit}>
                <input type="text" value={search} onChange={(e) => setSearch(e.target.value)}
                       onFocus={() => setIsOpen(true)}/>
                <input type="submit" value="Search"/>
            </form>
        </div>
        {renderMenu()}
    </div>
};

export default Search