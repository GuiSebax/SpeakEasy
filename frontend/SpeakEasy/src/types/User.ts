import { Course } from "./Course";

export interface User {
    id: number;
    name: string;
    email: string;
    password: string;
    level: number;
    xp?: number;
    createdAt: Date;
    courses: Set<Course>;
}