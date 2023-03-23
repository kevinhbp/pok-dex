/**
 * Created by Kevin Putro on 3/01/2023.
 *
 * kputro.app@gmail.com
 */
object ModuleDependencies {
	object Libraries {
		private const val LIBRARY = ":libraries"

		const val CORE = "$LIBRARY:core"

		const val SESSION = "$LIBRARY:session"
	}

	object Features {
		private const val FEATURES = ":features"

		const val HOME = "$FEATURES:home"

		const val DASHBOARD = "$FEATURES:dashboard"

		const val FORM = "$FEATURES:form"

		const val DETAIL = "$FEATURES:detail"

		const val SCAN = "$FEATURES:scan"

		const val PROFILE = "$FEATURES:profile"

		const val SETTING = "$FEATURES:setting"

		const val SYNCHRONIZER = "$FEATURES:synchronizer"
	}

	val features = arrayListOf<String>().apply {
		add(Features.HOME)
		add(Features.DASHBOARD)
		add(Features.FORM)
		add(Features.DETAIL)
		add(Features.SCAN)
		add(Features.PROFILE)
		add(Features.SETTING)
		add(Features.SYNCHRONIZER)
	}

	val libraries = arrayListOf<String>().apply {
		add(Libraries.CORE)
	}

	val session = arrayListOf<String>().apply {
		add(Libraries.SESSION)
	}
}