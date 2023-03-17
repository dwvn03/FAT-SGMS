<html>
	<head>
		<%@ include file="/WEB-INF/views/layouts/common-head.html" %>
		<title>Log in</title>
		<script src="https://accounts.google.com/gsi/client" async defer></script>
	</head>
	<body class="flex flex-col h-screen">
		<%@ include file="/WEB-INF/views/layouts/header.html" %>
		<main class="flex flex-initial container mx-auto flex-grow flex-wrap content-center">
			<div class="basis-1/2">
				<h1 class="mb-8 text-4xl">FPTU Attendance Taking<br> & Student Grade<br> Management System</h1>

				<div
					id="g_id_onload"
					data-client_id="601129434613-rn8fq8fe41iuraa80nu7sehoqa9o501d.apps.googleusercontent.com"
					data-context="signin"
					data-ux_mode="redirect"
					data-login_uri="/auth/google"
					data-auto_prompt="false"
				></div>

				<div
					class="g_id_signin"
					data-type="standard"
					data-shape="rectangular"
					data-theme="outline"
					data-text="signin_with"
					data-size="large"
					data-locale="en-US"
					data-logo_alignment="left"
				></div>
			</div>

			<div class="basis-1/2">
				<img class="inline-block max-w-md h-auto" src="/assets/images/logo.webp" alt="FAT-SGMS logo" onerror="this.onerror=null;this.src='/assets/images/logo.png';" />
			</div>
		</main>
		<%@ include file="/WEB-INF/views/layouts/footer.html" %>
	</body>
</html>
