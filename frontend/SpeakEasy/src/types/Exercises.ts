// import { Lesson } from "./Lesson";

export interface Exercises {
    id: number;
    lessonId: number;
    type: string;
    question: string;
    correctAnswer: string;
    options: string[];
    order: number;
}