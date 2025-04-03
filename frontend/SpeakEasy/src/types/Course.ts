import { User } from "./User";
import { Lesson } from "./Lesson";

export interface Course {
    id: number;
    name: string;
    description: string;
    nativeLanguage: string;
    targetLanguage: string;
    level: number;
    imgUrl: string;
    initialDate: Date;
    isActive: boolean;
    duration: number;
    users: Set<User>;
    lessons: Set<Lesson>;
}