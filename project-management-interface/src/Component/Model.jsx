

function Model(){
    return(
        <div className="Model_Container">
        <div className="Model_card">
        <div className="Model_header">
          <span className="Model_icon">
            <svg fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
              <path clip-rule="evenodd" d="M18 3a1 1 0 00-1.447-.894L8.763 6H5a3 3 0 000 6h.28l1.771 5.316A1 1 0 008 18h1a1 1 0 001-1v-4.382l6.553 3.276A1 1 0 0018 15V3z" fill-rule="evenodd"></path>
            </svg>
          </span>
          <p className="Model_alert">Message alert!</p>
        </div>
      
        <p className="Model_message">
          Thank you for registering to Project Masters, To verify please check your email.
        </p>
        <div className="Model_actions">
        <a className="Model_mark-as-read" href="/login">
        Click To Proceed
        </a>
        </div>
      </div>
      </div>
    );
}
export default Model;