export interface UserProgress {
    id: number;
    userId: number;
    courseId: number;
    lessonId: number;
    percentageCompleted: number;
    xpGained: number;
    lastAccessed: Date;
}