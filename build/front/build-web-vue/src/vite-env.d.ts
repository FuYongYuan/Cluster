/// <reference types="vite/client" />

declare module "skywalking-client-js" {
	const ClientMonitor: {
		register(options: Record<string, unknown>): void;
		setPerformance(options: Record<string, unknown>): void;
		reportFrameErrors(options: Record<string, unknown>, error: unknown): void;
	};
	export default ClientMonitor;
}
