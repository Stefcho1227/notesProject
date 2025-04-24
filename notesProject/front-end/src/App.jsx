import {useState, useEffect} from 'react';
import Header from './components/Header';
import NoteList from './components/NoteList';
import NoteEditor from './components/NoteEditor';
import './App.css'


function App() {
    const [notes, setNotes] = useState([]);
    const [activeNote, setActiveNote] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        const sampleNotes = [
            {id:1, title: 'Note 1', content: 'This is the first note', lastModified: new Date()},
            {id:2, title: 'Note 2', content: 'This is the second note', lastModified: new Date()}
        ];
        setNotes(sampleNotes);
    }, []);

    const filterNotes = notes.filter(note =>
        note.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
        note.content.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleAddNote = () => {
      const newNote = {
        id: Date.now(),
        title: 'Untitled Note',
        content: '',
        lastModified: new Date()
      };
      setNotes([newNote, ...notes]);
      setActiveNote(newNote);
    };

    const handleUpdateNote = (updatedNote) => {
        const updatedNotes = notes.map(note => 
            note.id === updatedNote.id ? {...updatedNote, lastModified: new Date()} : note
        );
        setNotes(updatedNotes);
        setActiveNote(updatedNote);
    };

    const handleDeleteNote = (id) => {
        setNotes(notes.filter(note => note.id !== id));
        if(activeNote && activeNote.id === id) {
            setActiveNote(null);
        }
    };

    return (
        <div className='app'>
            <Header
            onAddNote={handleAddNote}/>

            <div className='mainContent'>
                <NoteList
                notes={filterNotes}
                activeNote={activeNote}
                onNoteSelect={setActiveNote}
                onNoteDelete={handleDeleteNote}
                onSearch={setSearchTerm}/>

                <NoteEditor
                note={activeNote}
                onUpdateNote={handleUpdateNote}/>
            </div>
        </div>
    );
};

export default App;