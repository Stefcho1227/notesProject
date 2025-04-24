import React from "react";
import {useState, useEffect} from 'react';

const NoteEditor = ({note, onUpdateNote}) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    useEffect(() => {//може да ползваш react hook form, което ще ти спести такива ръчни валидации и ще олесни и ресетването на данните
        if (note) {
            setTitle(note.title);
            setContent(note.content);
        }
        else {
            setTitle('');
            setContent('');
        }
    }, [note]);

    const handleSave = () => {
        if(!note) return;
        onUpdateNote({...note, title, content});
    };

    if(!note) {
            return (
                <div className='noteEditor empty'>
                    <div className='emptyState'>
                        Select a note to edit or create a new one
                    </div>
                </div>
            );
        }

        return (
            <div className='noteEditor'>
                <input
                type='text'
                className='noteTitle'
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder='Note title'/>
                
                <textarea
                className='noteContent'
                value={content}
                onChange={(e) => setContent(e.target.value)}
                placeholder='Write your note here...'
                />

                <button onClick={handleSave} className='saveBtn'>Save</button>
            </div>
        );
};

export default NoteEditor;