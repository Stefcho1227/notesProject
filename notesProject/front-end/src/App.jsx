import React from "react";
import {useState, useEffect} from 'react';
import Header from './components/Header';
import NoteList from './components/NoteList';
import NoteEditor from './components/NoteEditor';
import './App.css'


function App() {
    const [notes, setNotes] = useState([]);
    const [activeNote, setActiveNote] = useState(null);
    {/*може да използваш готово компоненти, които да правят търсенето*/}
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {//useEffect ти трябва като фечваш, не в този контекст
        const sampleNotes = [
            {id:1, title: 'Note 1', content: 'This is the first note', lastModified: new Date()},
            {id:2, title: 'Note 2', content: 'This is the second note', lastModified: new Date()}
        ];
        setNotes(sampleNotes); //не ги сетвай така, направо си ги инициализирай като аргументи на useState
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
        <div className='app'>{/*кажи на чата да ти смени див-овете със секшъни*/}
            <Header onAddNote={handleAddNote}/>

            <div className='mainContent'>
                <NoteList notes={filterNotes} activeNote={activeNote} onNoteSelect={setActiveNote} onNoteDelete={handleDeleteNote} onSearch={setSearchTerm}/>
                {/*виж реакт редюсърите(те са точно за случаи, когато използваш много хендлър функции)*/}
                {/*вместо по този начин да симулираш смяна на страницата можеш да ползваш реакт рутер data mode, което ще махне нуждата от актив нот, даже ще може да си направиш и отделна страница при добавяне на нот, така че да не се налага да првиш дъмита*/}
                {/*с реакт рутер може да използваш екшъни и лоадъри, където може да изнесеш кода за извличане на данни, за добавяне или ъпдейтване*/}
                <NoteEditor note={activeNote} onUpdateNote={handleUpdateNote}/>
            </div>
        </div>
    );
}

export default App;