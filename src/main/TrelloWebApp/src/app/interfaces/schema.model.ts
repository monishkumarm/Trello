export interface User {
  id : bigint;
  fullName: string;
  email: string;
}

export interface Task {
  id: bigint;
  board: Board;
  taskStatus: TaskStatus,
  assignees: User[],
  name: string;
  description: string;
  speaker: string;
  image?: string;
  createdOn: Date;
  createdBy: User;
  issueType?: IssueType;
}

export enum IssueType {
  Task = 'task',
  SubTask = 'sub-task',
  Bug = 'bug',
  Epic = 'epic',
  Story = 'story'
}

export interface TaskStatus {
  name: string;
  tasks: Task[];
  id: string;
  board: Board
}

export interface Board {
  id: bigint;
  name: string;
  taskStatuses: TaskStatus[];
  createdBy: User;
  createdOn: Date;
}
