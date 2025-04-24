
const Header = ({onAddNote}) => {
    
    return (
        <header className='appHeader'>
            <h1>Cloud Notes</h1>
            <div>
                
                <button onClick={onAddNote} className='addNoteBtn'>
                    Add Note
                </button>
            </div>
        </header>
    );
};

export default Header;