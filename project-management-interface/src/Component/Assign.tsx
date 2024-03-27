import React from 'react'
import axios from 'axios';

function Assign(props) {
  const assignTask =async (memebrId) => {
    try {
      const response = await axios.post(`http://localhost:8080/assignee/saveAssignee/${memebrId}/
      ${props.assignModel.assigneeId}`);
      if (response.data) {window.location.reload()}
    } catch (error) {
        console.error("Error adding task: ", error);
    }
  }
  

  return (
    <>
      <div className="assign_section" style={{height:props.assignModel.assigneeHieght +"%"}}>
        <div className="assign_container">
          <i className="lni lni-close" onClick={()=>props.closeMode("assigneeHieght",0,0,'')}></i>
           <div className="task_members">
            <h6>Task members</h6>
            {
              props.taskMembers ?
                props.taskMembers.map((member,i) =>
              <div key={i} className="task_member_name">
                <span className='assignee_letters'>M</span>
                <div className='assignee_details'>
                  <span>{member.username}</span>
                  <span>email@email.com</span>
                </div>
              </div>
            ):""}
          </div>
          <div className="project_members_list">
            <h6>Project members list: </h6>
            {props.projectPeople.map((member,i ) =>
               <div key={i} className="project_members_names" onClick={()=>assignTask(member.member_id)}>
                  <div className="task_project_names_details">
                      <i className="lni lni-user" ></i>
                      <p>
                       <span>{member.username }</span>
                        <span>email@email.com</span>
                      </p>
                  </div>
                  <i className="lni lni-circle-plus invite_to_task"></i>
                </div>
            )}
          </div>
        </div>
      </div>
    </>
  )
}

export default Assign