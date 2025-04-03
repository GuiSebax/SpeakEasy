import { Course } from "./Course";

export interface Lesson {
    id: number;
    courseId: Course;
    title: string;
    description: string;
    order: number;
    isActive: boolean;
}