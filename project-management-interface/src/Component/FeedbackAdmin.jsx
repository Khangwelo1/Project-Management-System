import React, { useState } from 'react';
import Sidebar from './Sidebar';
import Navbar from './Navbar';

function FeedbackAdmin() {
    const [data, setData] = useState([
        { id: 1, Title: "Name", 
            feedback: " 1 If you want the container to scroll up and down rather than left and right", 
            opened: true
        },
        { id: 2, 
            Title: "Name", 
            feedback: " 2 If you want the container to scroll up and down rather than left and right", 
            opened: true 
        },
        { id: 3, 
            Title: "Name", 
            feedback: " If you want the container to scroll up and down rather than left and right", 
            opened: true
        },
        { id: 4, 
            Title: "Name", 
            feedback: " If you want the container to scroll up and down rather than left and right", 
            opened: true
        },
        { id: 5, 
            Title: "Name", 
            feedback: " 2 If you want the container to scroll up and down rather than left and right", 
            opened: true 
        },
        { id: 6, 
            Title: "Name", 
            feedback: " If you want the container to scroll up and down rather than left and right", 
            opened: true
        },
        { id: 7, 
            Title: "Name", 
            feedback: " If you want the container to scroll up and down rather than left and right", 
            opened: true 
        },
        { id: 8, 
            Title: "Name", 
            feedback: " 2 If you want the container to scroll up and down rather than left and right", 
            opened: true 
        },
        { id: 9, 
            Title: "Name", 
            feedback: " If you want the container to scroll up and down rather than left and right", 
            opened: true
        },
        { id: 10, 
            Title: "Name", 
            feedback: " If you want the container to scroll up and down rather than left and right", 
            opened: true
        }
    ]);

    const handleOpenMessage = (id) => {
        setData(prevData =>
            prevData.map(item =>
                item.id === id ? { ...item, opened: !item.opened } : item
            )
        );
    };

    const [showTrue, setShowTrue] = useState(true);
    const handleCheckedBoxChange = () => {
        setShowTrue(!showTrue);
    };

    const filteredItems = data.filter(item => item.opened === showTrue);
    const closedMessagesCount = data.filter(item => item.opened === false).length;
    return (
        <div className="page-row">
            <Sidebar />
            <div className="project-wrapper">
                <Navbar />
                <div className="Feed_Container">
                    <div className="card">
                        <div className="Header_Holder">
                            <h1>Feedback</h1>
                            <div className="Top_Containers_Holders">
                                <div className="Top_Containers">
                                    <h2>Number Of Problems Raised: {data.length}</h2>
                                </div>
                                <div className="Top_Containers">
                                    <h2>Number Of Problems Solved: {closedMessagesCount}</h2>
                                </div>
                            </div>
                        </div>
                        <div className="card__tags__Holder">
                            <div className="card__tags">
                                <ul className="tag">
                                    {filteredItems.map(item => (
                                        <li key={item.id} className="tag__name">
                                            <div className="contentsss">
                                                <div className="paragraph">
                                                    <b>{item.Title}</b>
                                                    {item.feedback}
                                                </div>
                                                <div>
                                                    <i className="lni lni-close" onClick={() => handleOpenMessage(item.id)}> </i>
                                                </div>
                                            </div>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default FeedbackAdmin;


            {/* <div className="title">
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            <span class="title_elements">
                Number of Users : {data.length}
            </span>
            </div>  */}